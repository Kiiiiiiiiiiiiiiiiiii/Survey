<template>
    <h1>Dashboard Summary </h1>
    <div class="table-container">
        <table>
            <thead>
            <th>행사명</th>
            <th>Today</th>
            <th>Total</th>
            <th>DB파일 다운로드</th>
            <th>NameCard 다운로드</th>
            </thead>
            <tbody>
            <tr v-for="item in branches" :key="item.id">
                <td>{{ item.name }}</td>
                <td>{{ item.todayCnt }}</td>
                <td>{{ item.totalCnt }}</td>
                <td>
                    <button @click="makeExcelFile(item.id, item.code)">다운로드</button>
                </td>
                <td>
                    <button @click="downloadImageFile(item.id, item.code)">다운로드</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import axios from "axios";
//import xlsx from 'xlsx' 로 사용하면 utils 사용할때 에러 발생
import * as xlsx from 'xlsx';

export default {
    name: "Result",
    data() {
        return {
            statData: [],
            todayData: [],
            totalData: [],
            branches: []
        };
    },
    methods: {
        getTodayStat(branchId) {
            axios.get('/api/stat', {
                params: {
                    branchId: branchId
                }
            }).then(res => {
                this.todayData = res.data;
            })
                .catch(error => {
                    console.log(error);
                })
        },
        refreshData() {
            this.showLangBtn = false
            //페이지 새로고침 시 호출될 api
            axios.get('/api/branches').then(res => {
                console.log('branches : ', res.data)
                this.branches = res.data;
                console.log(' this.branches : ', this.branches)
            })
                .catch(error => {
                    console.log(error);
                })
        },
        makeExcelFile(branchId, branchCode) {
            //실체 데이터 가져오기
            axios.get('api/getBranchUser', {
                params: {
                    branchId: branchId,
                    branchCode: branchCode
                }
            }).then(res => {
                const workBook = xlsx.utils.book_new()
                const workSheet = xlsx.utils.json_to_sheet(res.data)
                xlsx.utils.book_append_sheet(workBook, workSheet, 'survey')
                xlsx.writeFile(workBook, 'survey_user_list_' + branchCode + '.xlsx')
            }).catch(error => {
                console.log(error);
            })
        },
        async downloadImageFile(branchId, branchCode) {
            try {
                const formData = new FormData();
                formData.append('branchId', branchId)
                const response = await axios.post('api/downloadZipfile', formData, {
                    responseType: 'blob'
                });
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', 'namecardimg_' + branchCode + '.zip');
                document.body.appendChild(link);
                link.click();
            } catch (error) {
                console.log('img down error', error)
            }
        }
    },
    mounted() {
        this.showLangBtn = false
        // 30초(30000 밀리초)마다 refreshData 메서드를 호출
        this.refreshInterval = setInterval(this.refreshData, 30000);
        //페이지 로딩 시 호출될 api
        axios.get('/api/branches').then(res => {
            console.log('branches : ', res.data)
            this.branches = res.data;
            console.log(' this.branches : ', this.branches)
        })
            .catch(error => {
                console.log(error);
            })
    },
    beforeUnmount() {
        // 컴포넌트가 파괴될 때 setInterval을 해제
        clearInterval(this.refreshInterval);
    },
}
</script>

<style scoped>
.grid-container {
    border-collapse: collapse; /* 테두리가 겹치지 않도록 설정 */
    display: grid;
    grid-template-columns: 1fr 1fr; /* 2x2 그리드 생성 */
    gap: 10px; /* 그리드 아이템 간의 간격 설정 */
    text-align: center;
    justify-content: center;
    background-color: coral;
}

.table-container th, .table-container td {
    border: 2px solid #000; /* 테두리 스타일 설정 */
    border-radius: 100%; /* 테두리를 둥글게 만드는 속성 */
    padding: 8px; /* 내용과 테두리 간격 설정 */
    text-align: center;
}

.excel-button {
    text-align: center;
}

.img-button {
    text-align: center;
}
</style>

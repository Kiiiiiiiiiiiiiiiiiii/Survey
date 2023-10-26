<template>
    <h1>Dashboard Summary </h1>
    <div class="grid-container">
        <tr>
            <th>
                VIETNAM
                FOOD EXPO TODAY: {{ this.apiData.vnDailyCnt }}
            </th>
        </tr>
        <tr>
            <th>
                VIETNAM
                FOOD EXPO TOTAL: {{ this.apiData.vnTotalCnt }}
            </th>
        </tr>
        <tr>
            <th>
                SEOUL
                FOOD EXPO TODAY: {{ this.apiData.krDailyCnt }}
            </th>
        </tr>
        <tr>
            <th>
                SEOUL
                FOOD EXPO TOTAL: {{ this.apiData.krTotalCnt }}
            </th>
        </tr>
    </div>
    <br>
    <div class="excel-button">
        <button @click="makeExcelFile">Export Total Survey Data to Excel</button>
    </div>
    <div class="img-button">
        <button @click="downloadImageFile">Export namecard</button>
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
            apiData: [],
            tableData: []
        };
    },
    methods: {
        refreshData() {
            this.showLangBtn = false
            //페이지 로딩 시 호출될 api
            axios.get('/api/stat').then(res => {
                this.apiData = res.data;
                // console.log('apiData : ',this.apiData)
            })
                .catch(error => {
                    console.log(error);
                })
        },
        makeExcelFile() {
            //실체 데이터 가져오기
            axios.get('api/getAllUserData').then(res => {
                const workBook = xlsx.utils.book_new()
                const workSheet = xlsx.utils.json_to_sheet(res.data)
                xlsx.utils.book_append_sheet(workBook, workSheet, 'survey')
                xlsx.writeFile(workBook, 'survey_user_list.xlsx')
            }).catch(error => {
                console.log(error);
            })
        },
        async downloadImageFile() {
            try {
                const formData = new FormData();
                formData.append('branchId', 1)
                const response = await axios.post('api/downloadZipfile', formData,{
                    responseType: 'blob'
                });
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', 'namecardimg_kr.zip');
                document.body.appendChild(link);
                link.click();
            } catch (error) {
                console.log ('img down error', error)
            }
        }
    },
    mounted() {
        this.showLangBtn = false
        // 30초(30000 밀리초)마다 refreshData 메서드를 호출
        this.refreshInterval = setInterval(this.refreshData, 30000);
        //페이지 로딩 시 호출될 api
        axios.get('/api/stat').then(res => {
            this.apiData = res.data;
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

.grid-container th, .grid-container td {
    border: 2px solid #000; /* 테두리 스타일 설정 */
    border-radius: 100%; /* 테두리를 둥글게 만드는 속성 */
    padding: 8px; /* 내용과 테두리 간격 설정 */
    text-align: center;
    background-color: green;
}
.excel-button {
    text-align: center;
}
.img-button {
    text-align: center;
}
</style>

<template>
    <div class="home">
        Name: {{ name }} <input v-model="state.form.name"/>
    </div>
    <div>
        Company: {{ company }} <input v-model="state.form.company"/>
    </div>
    <div>
        E-Mail: {{ email }} <input v-model="state.form.email"/>
    </div>
    <div>
        Industry: {{ industry }}
        <select v-model="state.form.industry">
            <option>Confectionary</option>
            <option>Dessert</option>
            <option>HMR</option>
            <option>Alternative Food</option>
            <option>ETC</option>
        </select>
    </div>
    <div>
        Do you agree to the collection and use of your private information as described above?<br>
        <input type="radio" id="agree" value="true" v-model="picked"/>
        <label for="one">Agree</label>
        <input type="radio" id="disagree" value="false" v-model="picked"/>
        <label for="two">Disagree</label>
    </div>

    <button class="btn btn-primary w-100 py-2" @click="submit()">Submit</button>

</template>

<script>

import {reactive} from "vue";
import axios from "axios";
export default {
    setup() {
        const state = reactive({
            form: {
                name: "",
                company: "",
                email: "",
                industry: ""
            }
        })

        const submit = ()=>{
            axios.post("/api/submit", state.form).then((res)=>{
                console.log(res);
                window.alert("submit success")
            })
        }


        return {state, submit}
    }
}
</script>

<style scoped>
</style>

<template>
    <Logo/>
    <div class="start-aligned-image">
        <div class="container-element">
            {{ $t('name') }}: <input v-model="state.form.name"/>
        </div>
        <div class="container-element">
            {{ $t('company') }}: <input v-model="state.form.company"/>
        </div>
        <div class="container-element">
            {{ $t('email') }}: <input v-model="state.form.email"/>
        </div>
        <div class="container-element">
            {{ $t('industry') }}:
            <select v-model="state.form.industry">
                <option>{{ $t('confectionary') }}</option>
                <option>{{ $t('dessert') }}</option>
                <option>{{ $t('hmr') }}</option>
                <option>{{ $t('alternativeFood') }}</option>
                <option>{{ $t('etc') }}</option>
            </select>
        </div>
        <div class="container-element-agree">
            <p></p>
            {{ $t('consent') }}<br>
            <input type="radio" value="true" v-model="selected">
            <label for="one">{{ $t('agree') }}</label>
            <span></span>
            <input type="radio" value="false" v-model="selected">
            <label for="one">{{ $t('disagree') }}</label>
        </div>
        <br>
        <button class="btn btn-primary w-300 py-2" @click="submit()">{{ $t('submit') }}</button>
    </div>
</template>

<script>

import {reactive} from "vue";
import axios from "axios";
import Logo from "@/components/Logo.vue";
import {useRouter} from "vue-router";

export default {
    components: {Logo},
    setup() {
        const router = useRouter()

        const state = reactive({
            form: {
                name: "",
                company: "",
                email: "",
                industry: ""
            }
        })

        const submit = () => {
            axios.post("/api/submit", state.form).then((res) => {
                console.log(res.data)
                if (res.data) {
                    router.push({name: 'success'});
                } else {
                    router.push({name: 'duplicate'});
                }
            })
        }
        return {state, submit}
    }
}
</script>

<style scoped>
.locale{
    text-align: right;
}
.start-aligned-image {

    text-align: center;
}

.container-element {
    justify-content: center;
    align-items: center;
}

.container-element-agree {
    justify-content: center;
    align-items: center;
}
</style>

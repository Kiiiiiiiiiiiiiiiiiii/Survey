import {createI18n} from 'vue-i18n'
import ko from '@/locales/ko.json'
import en from '@/locales/en.json'
import vn from '@/locales/vn.json'

export const i18n = createI18n({
    locales: 'en',
    fallbacklocale: 'en',
    messages: {
        'ko': ko,
        'en': en,
        'vn': vn
    }
})
import { EncryptStorage } from "encrypt-storage";
import { defineStore } from "pinia";
import { ref } from "vue";

const encryptStorage = new EncryptStorage("abcdefghijklmnopqrstuvwxyz0123456789", { prefix: 'SJB' });

const useUserStore = defineStore('user',
    () => {
        const isLogin = ref(false);
        const checkLogin = () => {
            const key = "user";
            return encryptStorage.getItem(key);
        }
        const setWithEncrypt = (data) => {
            const key = "user";
            const item = {
                idx: data.idx,
                email: data.email,
                name: data.name
            }
            encryptStorage.setItem(key, JSON.stringify(item));
        }
        return { isLogin, checkLogin, setWithEncrypt }
    }
);

export default useUserStore;
import api from "@/plugins/axiosinterceptor";

// 백엔드 호출 후 받은 데이터를 반환
const signup = async (req) => {
    let data = {};
    let url = '/user/signup';

    await api.post(url, req)
        .then((res) => {
            data = res.data;
        })
        .catch((error) => {
            data = error.data;
        });

    return data;
}

// 백엔드 호출 후 받은 데이터를 반환
const login = async (req) => {
    let data = {};
    let url = '/user/login';

    await api.post(url, req)
        .then((res) => {
            data = res.data;
        })
        .catch((error) => {
            data = error.data;
        });

    return data;
}
export default { signup, login }
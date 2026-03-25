import api from "@/plugins/axiosinterceptor";

// 백엔드 호출 후 받은 데이터를 반환
const list = async (req) => {
    let data = {};
    let url = '/feed/list';

    await api.get(url, req)
        .then((res) => {
            data = res.data;
        })
        .catch((error) => {
            data = error.data;
        });

    return data;
}


// 백엔드 호출 후 받은 데이터를 반환
const likes = async (idx) => {
    let data = {};
    let url = `/likes/${idx}`;

    await api.get(url)
        .then((res) => {
            data = res.data;
        })
        .catch((error) => {
            data = error.data;
        });

    return data;
}

// 피드 등록
const register = async (formData) => {
    let data = {};
    let url = '/feed/reg';

    await api.post(url, formData)
        .then((res) => {
            data = res.data;
        })
        .catch((error) => {
            data = error.data;
        });

    return data;
}

export default { list, likes, register }
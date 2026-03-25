<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import api from '@/api/feed'

const router = useRouter()
const fileInput = ref(null)

const registerForm = reactive({
    contents: '',
    files: []
})

const imageUrls = ref([])

const onFileChange = (e) => {
    const files = e.target.files
    if (!files) return

    registerForm.files = Array.from(files)
    
    // 미리보기 이미지 생성
    imageUrls.value = []
    for (let i = 0; i < files.length; i++) {
        const reader = new FileReader()
        reader.onload = (event) => {
            imageUrls.value.push(event.target.result)
        }
        reader.readAsDataURL(files[i])
    }
}

const onSubmit = async () => {
    

    const data = await api.register({
        "contents": registerForm.contents
    })

    if (data && data.isSuccess) {
        alert('피드가 등록되었습니다.')
        router.push('/')
    } else {
        alert(data?.message || '피드 등록에 실패했습니다.')
    }
}

const triggerFileInput = () => {
    fileInput.value.click()
}
</script>

<template>
    <main class="main-container flex justify-content-center align-items-center">
        <section id="auth" class="flex direction-column">
            <div class="panel register-panel flex direction-column">
                <h2 class="flex justify-content-center">새 게시물 만들기</h2>
                <hr class="separator-line" />
                
                <form @submit.prevent="onSubmit()">
                

                    <input type="file" ref="fileInput" @change="onFileChange" multiple accept="image/*" style="display: none;" />
                    
                    <textarea v-model="registerForm.contents" placeholder="문구 입력..." class="contents-textarea"></textarea>
                    
                    <button type="submit" class="submitBtn">공유하기</button>
                </form>
            </div>
        </section>
    </main>
</template>

<style scoped>
.main-container {
    min-height: calc(100vh - 60px);
    background-color: #fafafa;
    padding-top: 60px;
}

#auth {
    width: 100%;
    max-width: 500px;
}

.panel {
    background-color: white;
    border: 1px solid #dbdbdb;
    border-radius: 10px;
    padding: 0;
    overflow: hidden;
}

h2 {
    font-size: 16px;
    font-weight: 600;
    padding: 10px;
    margin: 0;
}

.separator-line {
    border: 0;
    border-top: 1px solid #dbdbdb;
    margin: 0;
}

form {
    display: flex;
    flex-direction: column;
}

.image-upload-container {
    height: 300px;
    cursor: pointer;
    padding: 20px;
    text-align: center;
}

.image-upload-container p {
    margin: 20px 0;
    font-size: 18px;
    color: #262626;
}

.select-btn {
    background-color: #0095f6;
    color: white;
    border: none;
    padding: 5px 10px;
    border-radius: 4px;
    font-weight: 600;
    cursor: pointer;
}

.preview-container {
    padding: 10px;
    text-align: center;
}

.preview-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(100px, 1fr));
    gap: 10px;
    max-height: 300px;
    overflow-y: auto;
    margin-bottom: 10px;
}

.preview-image {
    width: 100%;
    aspect-ratio: 1/1;
    object-fit: cover;
    border-radius: 4px;
}

.reselect-btn {
    background: none;
    border: 1px solid #dbdbdb;
    padding: 5px 10px;
    border-radius: 4px;
    cursor: pointer;
    font-size: 12px;
}

.contents-textarea {
    border: none;
    border-top: 1px solid #dbdbdb;
    padding: 15px;
    height: 150px;
    resize: none;
    outline: none;
    font-size: 14px;
}

.submitBtn {
    background-color: #0095f6;
    color: white;
    border: none;
    padding: 10px;
    font-weight: 600;
    cursor: pointer;
    border-top: 1px solid #dbdbdb;
}

.submitBtn:hover {
    background-color: #1877f2;
}

.flex { display: flex; }
.direction-column { flex-direction: column; }
.justify-content-center { justify-content: center; }
.align-items-center { align-items: center; }
</style>

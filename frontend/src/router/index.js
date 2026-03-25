import { createRouter, createWebHistory } from "vue-router";
import useUserStore from "@/stores/useUserStore";



const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: () => import('@/layouts/DefaultLayout.vue'),
      children: [
        {
          name: "feedList",
          path: '',
          component: () => import("@/pages/feed/List.vue"),
          meta: {
            title: '메인 | 비스타그램',
            requiresAuth: true,
          },
        },
        {
          name: "feedRegister",
          path: '/feed/register',
          component: () => import("@/pages/feed/Register.vue"),
          meta: {
            title: '피드 등록 | 비스타그램',
            requiresAuth: true,
          },
        }
      ]
    },
    {
      path: '/user',
      component: () => import('@/layouts/UserLayout.vue'),
      children: [
        {
          name: "login",
          path: "login",
          component: () => import("@/pages/user/Login.vue"),
          meta: {
            title: '로그인 | 비스타그램',
            requiresAuth: false,
          },
        },
        {
          name: "signup",
          path: "signup",
          component: () => import("@/pages/user/SignUp.vue"),
          meta: {
            title: '회원가입 | 비스타그램',
            requiresAuth: false,
          },
        },
      ]
    },
  ],
});


/**
 * 전역 beforEach 가드
 *
 * 네비게이션이 트리거될 때마다 가드가 작성 순서에 따라 호출되기 전의
 * 모든 경우에 발생합니다. 가드는 비동기식으로 실행 될 수 있으며
 * 네비게이션은 모든 훅이 해결되기 전까지 보류 중으로 간주됩니다.
 */
router.beforeEach(async (to, from, next) => {
  document.title = to.meta.title || '비스타그램';

  //동일 패스일 경우 쿼리를 초기화 해준다.
  if (to.path == from.path) {
    //쿼리 제거
    to.fullPath = to.path;
    //쿼리 초기화
    to.query = {};
  }

  //저장소 정보 객체
  const userStore = useUserStore();


  if (userStore.checkLogin()) {
    next();
  } else if (to.meta.requiresAuth) {
    //===> 비로그인 상태에서 권한이 필요한 메뉴 접근시 안내 페이지 처리
    //로그인 페이지 이동
    next({ name: "login", query: { redirect: to.fullPath } });
  } else {
    //===> 비로그인 상태에서 권한이 필요 없는 메뉴 접근
    //패스
    next();
  }
});


export default router;

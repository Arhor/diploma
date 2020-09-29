import axios from 'axios';

import { SERVER_API_URL } from '@/config/server-api';

const mutation = {
    SET_ACCESS_TOKEN: 'SET_ACCESS_TOKEN',
    INVALIDATE_SESSION: 'INVALIDATE_SESSION',
};

export default {
    namespaced: true,
    state: {
        accessToken: null,
    },
    getters: {
        isLoggedIn: (state) => state.accessToken !== void 0 && state.accessToken !== null,
    },
    actions: {
        logIn: async ({ commit }, { username, password }) => {
            try {
                const {
                    data: { accessToken, tokenType },
                } = await axios.post(`${SERVER_API_URL}/auth/token`, {
                    username,
                    password,
                });
                commit(mutation.SET_ACCESS_TOKEN, { accessToken, tokenType });
            } catch (e) {
                commit(mutation.INVALIDATE_SESSION);
            }
        },
        logOut: ({ commit }) => {
            commit(mutation.INVALIDATE_SESSION);
        },
    },
    mutations: {
        [mutation.SET_ACCESS_TOKEN]: (state, { accessToken, tokenType }) => {
            state.accessToken = accessToken;
            state.tokenType = tokenType;
        },
        [mutation.INVALIDATE_SESSION]: (state) => {
            state.accessToken = null;
            state.tokenType = null;
        },
    },
};
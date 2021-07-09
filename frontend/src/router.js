
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import CarAllocationRequestManager from "./components/CarAllocationRequestManager"

import DrivingManager from "./components/DrivingManager"

import PaymentManager from "./components/PaymentManager"


import DrivingInfo from "./components/DrivingInfo"
import MessageManager from "./components/MessageManager"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/CarAllocationRequest',
                name: 'CarAllocationRequestManager',
                component: CarAllocationRequestManager
            },

            {
                path: '/Driving',
                name: 'DrivingManager',
                component: DrivingManager
            },

            {
                path: '/Payment',
                name: 'PaymentManager',
                component: PaymentManager
            },


            {
                path: '/DrivingInfo',
                name: 'DrivingInfo',
                component: DrivingInfo
            },
            {
                path: '/Message',
                name: 'MessageManager',
                component: MessageManager
            },



    ]
})

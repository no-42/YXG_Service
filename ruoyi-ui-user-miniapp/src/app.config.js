export default defineAppConfig({
    pages: [
        'pages/home/home',
        'pages/shop/shop',
        'pages/user/user',
        'pages/user/login',
        'pages/home/search/search'
    ],
    tabBar: {
        color: '#000',
        selectedColor: '#6cf',
        backgroundColor: '#fff',
        borderStyle: 'white',
        position: 'bottom',
        list: [
            {
                pagePath: 'pages/home/home',
                text: '首页',
                iconPath: 'img/tabbar/home.png',
                selectedIconPath: 'img/tabbar/home_.png'
            },
            {
                pagePath: 'pages/shop/shop',
                text: '商城',
                iconPath: 'img/tabbar/shop.png',
                selectedIconPath: 'img/tabbar/shop_.png'
            },
            {
                pagePath: 'pages/user/user',
                text: '我的',
                iconPath: 'img/tabbar/user.png',
                selectedIconPath: 'img/tabbar/user_.png'
            },
        ]
    },
    window: {
        backgroundTextStyle: 'light',
        navigationBarBackgroundColor: '#fff',
        navigationBarTitleText: 'WeChat',
        navigationBarTextStyle: 'black'
    }
})

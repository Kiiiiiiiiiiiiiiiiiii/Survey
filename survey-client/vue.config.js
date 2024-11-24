module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://35.216.123.244:8080',
       /* target: 'http://35.216.123.244:8080/survey-api',*/
        //target: 'http://localhost:8080',
        changeOrigin: true,
        pathRewrite: {
          /*'^/api': ''  // 요청 경로에서 '/api' 제거*/
          '^/': ''
        }
      }
    }
  }
}
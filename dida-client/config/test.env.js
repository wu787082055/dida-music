'use strict'
const merge = require('webpack-merge')
const devEnv = require('./dev.env')

module.exports = merge(devEnv, {
  NODE_ENV: '"testing"',
  NODE_HOST: '"http://127.0.0.1:7688"' // 测试环境
})

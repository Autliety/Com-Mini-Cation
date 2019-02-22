import Taro from '@tarojs/taro'

export default function taroRequest () {
  let option = {
    url: 'test'
  }
  Taro.request(option)
  .then(res => console.log(res))
}

import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
//import './index.scss'

export default class EchoWall extends Taro.Component {

  state = {}

  render () {
    return (
      <View className='echo'>
        wall
      </View>
    )
  }
}

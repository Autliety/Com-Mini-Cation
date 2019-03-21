import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'

type Props = any

export default class SelfQaPage extends Taro.Component<Props> {

  state = {}

  render () {
    return (
        <View className='SelfQaPage'>
          SelfQaPage
        </View>
    )
  }
}

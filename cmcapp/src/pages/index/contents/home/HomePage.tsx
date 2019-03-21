import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'

type Props = any
type State = any

export default class HomePage extends Taro.Component<Props, State> {

  constructor (props) {
    super(props)
  }

  render () {
    return (
      <View className='HomePage'>
        MyHomePage
      </View>
    )
  }
}

import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'

type Props = any

export default class AppointPage extends Taro.Component<Props> {

  state = {}

  componentWillUpdate (): void {
    console.log("willUpdate")
  }

  componentWillMount (): void {
    console.log("willMount")
  }

  componentWillReceiveProps (): void {
    console.log("willRP")
  }

  render () {
    return (
        <View className='AppointPage'>
          AppointPage

        </View>
    )
  }
}

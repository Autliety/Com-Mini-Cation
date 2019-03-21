import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtButton, AtSearchBar } from 'taro-ui'

import './EchoWallPage.scss'

type Props = any
type State = { value: string }

export default class EchoWallPage extends Taro.Component<Props, State> {

  constructor (props) {
    super(props)
    this.state = {
      value: ''
    }
  }

  handleChange = (value) => {
    this.setState({
      value
    })
  }

  render () {
    return (
      <View className='EchoWallPage'>
        <View className='at-row'>
          <View className='at-col at-col-2 new-button'>
            <AtButton
              type='secondary'
              size='small'
              circle
            >
              新建
            </AtButton>
          </View>
          <View className='at-col at-col-10'>
            <AtSearchBar
              onChange={this.handleChange}
              value={this.state.value}
            />
          </View>
        </View>
      </View>
    )
  }
}

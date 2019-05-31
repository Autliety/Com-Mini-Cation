import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtTabBar } from 'taro-ui'
import getProps from '../../../utils/properties'
//import './tabbar.scss'

type Props = {
  onTabChange: (value: number) => void
}

export default class TabBar extends Taro.Component<Props> {

  state = {
    current: 0
  }

  handleClickTab = (value) => {
    this.setState({
      current: value
    })
    this.props.onTabChange(value)
  }

  render () {
    const tabList = getProps('tabList')
    return (
      <View className='tabbar'>
        <AtTabBar
          fixed
          tabList={tabList}
          current={this.state.current}
          onClick={this.handleClickTab}
        />
      </View>
    )
  }
}

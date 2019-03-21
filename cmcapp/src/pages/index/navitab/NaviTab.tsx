import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtTabBar } from 'taro-ui'
import { getGlobalData } from '../../../utils/global_data'

type Props = { onPageChange: (index: number) => void }

export default class NaviTab extends Taro.Component<Props> {

  state = {
    current: 0
  }

  tabList = getGlobalData('TAB_LIST')

  handleClick = (current: number) => {
    this.setState({
      current
    })
    Taro.setNavigationBarTitle({
      title: current === 0 ?
        getGlobalData('APP_NAME')
        :
        this.tabList[current].title
      }
    )
    this.props.onPageChange(current)
  }

  render () {
    return (
      <View className='BottomTabBar'>
        <AtTabBar
          fixed
          tabList={this.tabList}
          current={this.state.current}
          onClick={this.handleClick}

        />
      </View>
    )
  }
}

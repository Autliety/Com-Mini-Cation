import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'

import './index.scss'

import TabBar from './tabbar'
import getProps from '../../utils/properties'
import Home from './contents/home'
import SelfQna from './contents/selfqna'
import EchoWall from './contents/echowall'
import Appoint from './contents/appoint'
import Login from './login'
import { initData } from './actions'

export default class Index extends Taro.Component {

  config: Taro.Config = {
    navigationBarTitleText: '通小信'
  }

  state = {
    inLogin: true,
    onTab: -1
  }

  handleLoginSuccess = () => {
    initData().then(() => {
      this.setState({
        inLogin: false,
        onTab: 0
      })
    })
  }

  handleTabChange = (value) => {
    const title = value === 0 ? getProps('appName') : getProps('tabList')[value].title
    Taro.setNavigationBarTitle({title})
    .then(() => {
      this.setState({
        onTab: value
      })
    })
  }

  render () {
    let content
    switch (this.state.onTab) {
      case 0: {
        content = <Home />
        break
      }
      case 1: {
        content = <SelfQna />
        break
      }
      case 2: {
        content = <EchoWall />
        break
      }
      case 3: {
        content = <Appoint />
        break
      }
    }

    return (
      <View className='home'>
        {this.state.inLogin && <Login onLoginSuccess={this.handleLoginSuccess} />}
        {content}
        <TabBar onTabChange={this.handleTabChange} />
      </View>
    )
  }
}

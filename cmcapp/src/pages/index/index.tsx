import Taro, { Config } from '@tarojs/taro'
import { View } from '@tarojs/components'

import './index.scss'

import NaviTab from './navitab/NaviTab'
import EchoWallPage from './contents/echowall/EchoWallPage'
import Login from './login/Login'
import HomePage from './contents/home/HomePage'
import SelfQaPage from './contents/selfqa/SelfQaPage'
import AppointPage from './contents/appoint/AppointPage'

export default class Index extends Taro.Component {

  state = {
    onPage: 0,
  }

  config: Config = {
    navigationBarTitleText: '通小信WIP'
  }

  handleLogin = () => {
  }

  handlePageChange = (index: number) => {
    this.setState({
      onPage: index
    })
  }

  render () {
    let content
    switch (this.state.onPage) {
      case 0: {
        content = <HomePage />
        break
      }
      case 1: {
        content = <SelfQaPage />
        break
      }
      case 2: {
        content = <AppointPage />
        break
      }
      case 3: {
        content = <EchoWallPage />
        break
      }
    }

    return (
      <View className='index'>
        {null &&
        <Login onLogin={this.handleLogin} />
        }
        {content}
        <NaviTab
          onPageChange={this.handlePageChange}
        />
      </View>
    )
  }
}


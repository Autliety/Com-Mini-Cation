import Taro, { Config } from '@tarojs/taro'
import { View } from '@tarojs/components'

import './index.scss'

import NaviTab from './navitab/NaviTab'
import Login from './login/Login'
import IndexContent from './contents/IndexContent'

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
    return (
      <View className='index'>
        <Login onLogin={this.handleLogin} />
        <IndexContent active={this.state.onPage} />
        <NaviTab onPageChange={this.handlePageChange} />
      </View>
    )
  }
}


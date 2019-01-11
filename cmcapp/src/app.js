import Taro, { Component } from '@tarojs/taro'
import Index from './pages/index'

import './app.less'

// uncomment below to enable React-Devtools under h5:
/*
 if (process.env.NODE_ENV !== 'production' && process.env.TARO_ENV === 'h5')  {
   require('nerv-devtools')
 }
*/

class App extends Component {

  config = {
    pages: [
      'pages/index/index'
    ],
    window: {
      backgroundTextStyle: 'light',
      navigationBarBackgroundColor: '#fff',
      navigationBarTitleText: 'WeChat',
      navigationBarTextStyle: 'black'
    }
  }

  componentDidMount () {}

  componentDidShow () {}

  componentDidHide () {}

  componentDidCatchError () {}

  // render() in app.js acts nothing, don't edit it
  render () {
    return (
      <Index />
    )
  }
}

Taro.render(<App />, document.getElementById('app'))

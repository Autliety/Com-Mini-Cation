import '@tarojs/async-await'
import 'taro-ui/dist/style/index.scss'

import Taro from '@tarojs/taro'
import Index from './pages/index'

import './app.scss'

// Uncomment this to enable React-Devtools in h5 env:
// if (process.env.NODE_ENV !== 'production' && process.env.TARO_ENV === 'h5')  {
//   require('nerv-devtools')
// }

class App extends Taro.Component {


  config: Taro.Config = {
    pages: [
      'pages/index/index',
    ],
    window: {
      navigationBarBackgroundColor: '#71A0F8',
      // navigationBarBackgroundColor: '#FFF',
      navigationBarTitleText: 'WeChat',
      navigationBarTextStyle: 'black',

      backgroundTextStyle: 'light'
    },
    debug: true // TODO disable debug
  }

  // DON'T modify this render!
  render () {
    return (
      <Index />
    )
  }
}

Taro.render(<App />, document.getElementById('app'))

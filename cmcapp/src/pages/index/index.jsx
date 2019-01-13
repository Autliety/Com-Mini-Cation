import Taro from '@tarojs/taro'
import {View, Text, Button} from '@tarojs/components'
import {AtButton} from 'taro-ui'
import './index.scss'

const APP = Taro.getApp();

export default class Index extends Taro.Component {

  constructor(props) {
    super(props);
    this.config = {
      navigationBarTitleText: 'index'
    };
    this.state = {
      nickName: 'Guest',
      number: undefined
    };
  }

  componentWillMount() {

    Taro.getUserInfo()
    .then(res => {
      APP.globalData = {
        userInfo: res.userInfo
      };
      this.setState({
        nickName: res.userInfo.nickName
      });
      let option = {
        url: 'http://localhost:8080/hello',
        method: 'POST',
        data: {name: res.userInfo.nickName}
      };
      Taro.request(option)
      .then(res => {
        this.setState({
          number: res.data.number
        });
      })
      .catch(err => {
        console.log('newErr', err);
      })
    })
    .catch(err => {
      console.log('userErr', err);
    });
  }

  render() {
    return (
      <View className={'at-article'}>
        <View className={'at-article__h1'}>Hello, {' ' + this.state.nickName}: </View>
        {this.state.number === undefined ?
          <AtButton type={'primary'} circle={true} openType={'getUserInfo'}
                    onGetUserInfo={this.componentWillMount}>Login</AtButton>
          :
          <View className={'at-article__p'}>You are the{' ' + this.state.number}th user to
            login</View>
        }
      </View>
    )
  }
}



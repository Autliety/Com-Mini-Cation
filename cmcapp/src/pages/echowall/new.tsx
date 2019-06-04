import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtButton, AtDivider, AtInput, AtTextarea } from 'taro-ui'
import { newQuestion } from './actions'
//import './new.scss'

type Props = any

export default class EchoWallNew extends Taro.Component<Props> {

  config: Taro.Config = {
    navigationBarTitleText: '创建新留言'
  }

  state = {
    titleValue: '',
    kwValue: '',
    contentValue: ''
  }

  handleTitleChange = (value) => {
    this.setState({
      titleValue: value
    })
  }

  handleKwChange = (value) => {
    this.setState({
      kwValue: value
    })
  }

  handleContentChange = (e) => {
    this.setState({
      contentValue: e.target.value
    })
  }

  handleClickSubmit = () => {
    const {titleValue, kwValue, contentValue} = this.state
    newQuestion(titleValue, contentValue, kwValue.split(' '))
    .then(() => {
      Taro.navigateBack({delta: 1})
    })
  }

  render () {
    return (
      <View className='echo-wall-new'>

        <AtDivider content='标题' />

        <AtInput
          name='value'
          placeholder='请输入留言的标题'
          value={this.state.titleValue}
          onChange={this.handleTitleChange}
        />

        <AtDivider content='关键词' />

        <AtInput
          name='keywords'
          placeholder='用空格隔开多个关键词'
          value={this.state.kwValue}
          onChange={this.handleKwChange}
        />

        <AtDivider content='详细内容' />

        <View style='margin:20px'>
          <AtTextarea
            value={this.state.contentValue}
            onChange={this.handleContentChange}
            maxLength={120}
            placeholder='留言的详细内容。。。'
          />
        </View>

         <View style='padding:10px'>
            <AtButton
              type='primary'
              circle
              onClick={this.handleClickSubmit}
            >
              创建留言
            </AtButton>
          </View>
      </View>
    )
  }
}

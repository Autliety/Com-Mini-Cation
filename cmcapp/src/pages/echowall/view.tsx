import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtDivider, AtCard, AtTag, AtList, AtListItem, AtInput, AtForm, AtButton } from 'taro-ui'

import { addAnswer, getEchoWall } from './actions'
//import './index.scss'

type Props = any

export default class EchoWallView extends Taro.Component<Props> {

  config: Taro.Config = {
    navigationBarTitleText: '查看回音壁留言'
  }

  state = {
    data: {
      question: {} as Question,
      answers: [] as Answer[]
    },
    value: ''
  }

  handleTextChange = (value: string) => {
    this.setState({
      value
    })
  }

  handleClickButton = () => {
    const {value} = this.state
    if (value === '') {
      Taro.showToast({
        icon: 'none',
        title: '答复内容不能为空'
      })
    } else {
      addAnswer(this.$router.params.id, this.state.value)
      .then(() => {
        this.componentWillMount()
      })
    }

  }

  componentWillMount () {
    getEchoWall(this.$router.params.id)
    .then((data) => {
      this.setState({
        value: '',
        data
      })
    })
  }

  render () {
    const {question, answers} = this.state.data

    return (
      <View className='echo-wall-view'>

        <AtDivider content='留言详情' />

        <View>
          <AtCard
            className='question'
            title={question.title}
            note={'最后更新: ' + question.updateTime}
          >
            <View>{question.context}</View>
            <View style='flex-direction:row;margin-top:10px;'>
              {question.tags.map((t, i) => {
                return <AtTag
                  key={i}
                  customStyle='margin-right:5px;'
                  size='small'
                  circle
                  active
                >
                  {t}
                </AtTag>
              })}
            </View>
          </AtCard>
        </View>

        <AtDivider content='所有回复' />

        <View style='padding-bottom:100px'>
          <AtList>
            {answers.map((a, i) => {
              return <AtListItem
                key={i}
                title={a.context}
                note={a.updateTime}
              />
            })}
          </AtList>
        </View>

        <View style='position:fixed;bottom:0;width:100%;'>
          <AtForm>
            <AtInput
              name='value'
              placeholder='添加新的回复'
              value={this.state.value}
              onChange={this.handleTextChange}
            >
              <AtButton
                customStyle='margin-right:15px;'
                type='primary'
                size='small'
                onClick={this.handleClickButton}
              >
                回复
              </AtButton>
            </AtInput>
          </AtForm>
        </View>
      </View>
    )
  }
}

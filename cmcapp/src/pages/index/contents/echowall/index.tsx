import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
// @ts-ignore
import { AtDivider, AtCard, AtTag, AtFab, AtIcon, AtFloatLayout } from 'taro-ui'
import './index.scss'

type Props = {
  ist: boolean,
  data: Question[]
}

export default class EchoWall extends Taro.Component<Props> {

  divider = Taro.getStorageSync('isTeacher')

  handleClickCard = (id: number) => {
    Taro.navigateTo({url: '/pages/echowall/view?id=' + id})
  }

  handleClickAdd = () => {
    Taro.navigateTo({url: '/pages/echowall/new'})
  }

  componentDidShow () {
    Taro.startPullDownRefresh()
  }

  render () {
    const {ist, data} = this.props
    const divider = ist ? '学生留言' : '我的留言'

    return (
      <View className='echo'>

        <View className='at-fab-container'>
          <AtFab onClick={this.handleClickAdd}>
            <AtIcon className='at-fab__icon' value='add' />
          </AtFab>
        </View>

        <AtDivider content={divider} />

        <View className='ew-content'>
          {data.map((q, index) => {
            return <View key={index} className='card-list-container'>
              <AtCard
                className='question'
                title={q.title}
                note={'最后更新: ' + q.updateTime}
                onClick={() => this.handleClickCard(q.id)}
              >
                <View style='flex-direction:row;'>
                  {q.tags.map((t, i) => {
                    return t !== '' && <AtTag
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
          })}
        </View>


      </View>
    )
  }
}

import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
// @ts-ignore
import { AtDivider, AtCard, AtTag, AtFab, AtIcon } from 'taro-ui'
import { isTeacher } from '../../../../utils/debuging'
import { getData } from '../../../../utils/globalData'
import './index.scss'

export default class EchoWall extends Taro.Component {

  state = {}

  handleClickCard = (id: number) => {
    console.log(id)
  }

  handleClickAdd = () => {

  }

  render () {
    const ewList = getData('ewList')

    return (
      <View className='echo'>

        <View className='at-fab-container'>
          <AtFab onClick={this.handleClickAdd}>
            <AtIcon className='at-fab__icon' value='add' />
          </AtFab>
        </View>

        <AtDivider content={isTeacher() ? '学生留言' : '我的留言'} />

        <View className='ew-content'>
          {ewList.map((q, index) => {
            return <View key={index} className='card-list-container'>
              <AtCard
                className='question'
                title={q.title}
                note={'最后更新: ' + q.updateTime}
                onClick={() => this.handleClickCard(q.id)}
              >
                <View style='flex-direction:row;'>
                  {q.tags.map((t, i) => {
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
          })}
        </View>
      </View>
    )
  }
}

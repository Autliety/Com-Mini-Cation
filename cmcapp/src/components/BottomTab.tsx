import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtTabBar } from 'taro-ui'

type Props = { active: number, onChange: (index: number) => void }
type State = any

export default class BottomTab extends Taro.Component<Props, State> {

  constructor (props) {
    super(props)
    this.state = {
      dot: true,
      txt: true
    }
  }

  handleClickTab = (value) => {
    this.props.onChange(value)
    this.setState({
      dot: value !== 1,
      txt: value !== 3
    })
  }

  render () {
    const bellText = this.state.txt ? 'new' : '1'
    return (
      <View className='BottomTabBar'>
        <AtTabBar
          fixed
          tabList={[
            {title: '主页', iconType: 'home'},
            {title: '自助问答', iconType: 'tags', dot: this.state.dot},
            {title: '预约服务', iconType: 'clock'},
            {title: '回音壁', iconType: 'bell', text: bellText}
          ]}
          onClick={this.handleClickTab}
          current={this.props.active}
        />
      </View>
    )
  }
}

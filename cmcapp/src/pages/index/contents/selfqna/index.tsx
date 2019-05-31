import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtSearchBar, AtDivider, AtList, AtListItem } from 'taro-ui'
import { getData } from '../../../../utils/globalData'
// import './index.scss'
import Picks from './picks'
import { search } from './actions'

const initPicking = {
  id: -1,
  title: '',
  summary: '',
  fileName: ''
}

export default class SelfQna extends Taro.Component {

  state = {
    value: '',
    searching: false,
    searchList: [],

    picks: initPicking

  }

  handleTextChange = (value) => {
    this.setState({
      value,
      searching: value !== '',
      searchList: [],
      picks: initPicking
    })
  }

  handleClickSearch = (key: string) => {
    search(key)
    .then((searchList) => {
      if (searchList.length === 0) {
        Taro.showToast({
          title: '找不到相关内容',
          icon: 'none',
          duration: 2000
        })
      } else {
        this.setState({
          searchList
        })
      }
    })
  }

  handleClickList = (value) => {
    this.setState({
      picks: value
    })
  }

  handlePickExit = () => {
    this.setState({
      picks: initPicking
    })
  }

  render () {
    const {value, searching, searchList, picks} = this.state
    const qnaList = searching ? searchList : getData('qnaList')

    return (
      <View className='selfqna'>
        <View className='search'>
          <AtSearchBar
            value={value}
            onChange={this.handleTextChange}
            onActionClick={() => this.handleClickSearch(value)}
          />
        </View>

        {qnaList.length !== 0 &&
        <AtDivider content={searching ? '搜索结果' : '最近更新'} />
        }

        <AtList>
          {qnaList.map((d, index) => {
            return <AtListItem
              key={index}
              title={d.title}
              note={d.fileName}
              arrow='right'
              onClick={() => this.handleClickList(d)}
            />
          })}
        </AtList>

        <Picks
          picks={picks}
          onExit={this.handlePickExit}
        />

      </View>
    )
  }
}

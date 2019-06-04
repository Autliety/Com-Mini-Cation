import Taro from '@tarojs/taro'
import { downloadRequest, getRequest } from '../../../../utils/request'

export async function search (key: string): Promise<Document[]> {
  const res = await getRequest('/selfqna', {search: key})
  return res.results
}

export async function download (id: number) {
  const path = '/selfqna/' + id
  const filePath = await downloadRequest(path)
  await Taro.openDocument({
    filePath,
    fileType: 'docx'
  })
}

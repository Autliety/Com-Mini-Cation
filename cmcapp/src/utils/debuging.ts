import { getData } from './globalData'

export async function sleep (ms: number) {
  console.info('begin sleep for ' + ms + 'ms')
  await new Promise(resolve => setTimeout(resolve, ms))
  console.info('awaken')
}

export function isTeacher () : boolean {
  return getData('cmcUser').roles.indexOf('STUDENT') < 0
}

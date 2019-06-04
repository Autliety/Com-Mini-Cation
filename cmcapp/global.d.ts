declare module "*.png";
declare module "*.gif";
declare module "*.jpg";
declare module "*.jpeg";
declare module "*.svg";
declare module "*.css";
declare module "*.less";
declare module "*.scss";
declare module "*.sass";
declare module "*.styl";

// @ts-ignore
declare const process: {
  env: {
    TARO_ENV: 'weapp' | 'swan' | 'alipay' | 'h5' | 'rn' | 'tt';
    [key: string]: any;
  }
}

// custom types
interface Entity {
  id: number
}

interface CmcUser extends Entity {
  realName: string,
  schoolId: string,
  phoneNum: string,
  roles: string[]
}

interface Document extends Entity {
  fileName: string,
  title: string,
  summary: string
}

interface Question extends Entity {
  title: string,
  context: string,
  tags: string[],
  updateTime: string
}

interface Aptment extends Entity {
  user: CmcUser,
  distUser: CmcUser,
  summary: string,
  appointDate: string,
  time: 'AM' | 'PM',
  status: 'NEW' | 'CONFIRMED' | 'CANCELED'
}

interface GlobalData {
  cmcUser: CmcUser,
  docList: Document[],
  ewList: Question[],
  aptList: Aptment[]
}

interface Answer extends Entity {
  context: string,
  updateTime: string
}

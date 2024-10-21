export interface StateChangeRequest {
  type: StateChangeRequestType;
}

export const enum StateChangeRequestType {
  PAGE_ADD = 'PAGE_ADD',
  BLOCK_ADD = 'BLOCK_ADD',
  BLOCK_SELECT = 'BLOCK_SELECT',
  BLOCK_UPDATE_PROPS = 'BLOCK_UPDATE_PROPS'
}
export interface StateChangeRequest {
  type: StateChangeRequestType;
}

export const enum StateChangeRequestType {
  PAGE_ADD = 'PAGE_ADD',
  PAGE_DELETE = 'PAGE_DELETE',
  PAGE_UPDATE = 'PAGE_UPDATE',

  BLOCK_ADD = 'BLOCK_ADD',
  BLOCK_SELECT = 'BLOCK_SELECT',
  BLOCK_UPDATE_PROPS = 'BLOCK_UPDATE_PROPS',

  LEFT_PANEL_CHANGE = 'LEFT_PANEL_CHANGE'
}
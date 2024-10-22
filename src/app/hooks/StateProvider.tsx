import React, { createContext, useContext, useReducer } from "react";
import { ApplicationState } from "./ApplicationState";
import { BlockAddRequest } from "./block/BlockAddRequest";
import { BlockSelectRequest } from "./block/BlockSelectRequest";
import { BlockUpdatePropsRequest } from "./block/BlockUpdatePropsRequest";
import { PageAddRequest } from "./page/PageAddRequest";
import { PageDeleteRequest } from "./page/PageDeleteRequest";
import { PageSelectRequest } from "./page/PageSelectRequest";
import { PageUpdateRequest } from "./page/PageUpdateRequest";
import { LeftPanelChangeRequest } from "./sidebar/LeftPanelChangeRequest";
import { StateChangeRequest, StateChangeRequestType } from "./StateChangeRequest";

const initialState = new ApplicationState();

function stateReducer(state: ApplicationState, action: StateChangeRequest) {
  console.log('Dispatching action ' + action.type)

  switch (action.type) {
    case StateChangeRequestType.PAGE_ADD: {
      const payload = action as PageAddRequest
      return state.withPage(payload.page);
    }
    case StateChangeRequestType.PAGE_DELETE: {
      const payload = action as PageDeleteRequest
      return state.withoutPage(payload.page)
    }
    case StateChangeRequestType.PAGE_UPDATE: {
      const payload = action as PageUpdateRequest
      return state.withUpdatedPage(payload.page, payload.callback)
    }
    case StateChangeRequestType.PAGE_SELECT: {
      const payload = action as PageSelectRequest
      return state.withCurrentPage(payload.page)
    }
    case StateChangeRequestType.BLOCK_ADD: {
      const payload = action as BlockAddRequest;
      return state.withBlock(payload.page, payload.block)
    }
    case StateChangeRequestType.BLOCK_SELECT: {
      const payload = action as BlockSelectRequest;
      return state.withCurrentBlock(payload.block);
    }
    case StateChangeRequestType.BLOCK_UPDATE_PROPS: {
      const payload = action as BlockUpdatePropsRequest<any>
      return state.withUpdatedBlock(payload.block, payload.callback)
    }
    case StateChangeRequestType.LEFT_PANEL_CHANGE: {
      const payload = action as LeftPanelChangeRequest;
      return state.withLeftPane(payload.componentAlias);
    }
    default: console.error('No reducer for action of type ' + action.type)
  }

  return state;
}

interface StateContextValue {
  state: ApplicationState;
  dispatch: React.Dispatch<StateChangeRequest>
}

export const StateContext = createContext<StateContextValue>({
  state: initialState,
  dispatch: (action) => console.log("Smth strange has happened")
});

export function StateProvider({ children }: any) {
  const [state, dispatch] = useReducer(stateReducer, initialState);

  return (
    <StateContext.Provider value={{ state, dispatch }}>
      {children}
    </StateContext.Provider>
  );
}

export function useAppState() {
  return useContext(StateContext);
}
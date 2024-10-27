'use client';

import React, { createContext, useContext, useReducer } from "react";
import { ApplicationState } from "./ApplicationState";
import { BlockAddRequest } from "./block/BlockAddRequest";
import { BlockSelectRequest } from "./block/BlockSelectRequest";
import { BlockUpdatePropsRequest } from "./block/BlockUpdatePropsRequest";
import { DashboardLoadDataRequest } from "./global/DashboardLoadDataRequest";
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
      const payload = action as BlockAddRequest<object>;
      return state.withBlock(payload.page, payload.block)
    }
    case StateChangeRequestType.BLOCK_SELECT: {
      const payload = action as BlockSelectRequest<object>;
      return state.withCurrentBlock(payload.block);
    }
    case StateChangeRequestType.BLOCK_UPDATE_PROPS: {
      const payload = action as BlockUpdatePropsRequest<object>
      return state.withUpdatedBlock(payload.block, payload.callback)
    }
    case StateChangeRequestType.LEFT_PANEL_CHANGE: {
      const payload = action as LeftPanelChangeRequest;
      return state.withLeftPane(payload.componentAlias);
    }
    case StateChangeRequestType.DASHBOARD_LOAD_DATA: {
      const payload = action as DashboardLoadDataRequest;
      return state.withInitialData(payload.data);
    }
    default: console.error('No reducer for action of type ' + action.type + '. Update State Provider')
  }

  return state;
}

interface StateContextValue {
  state: ApplicationState;
  dispatch: React.Dispatch<StateChangeRequest>
}

export const StateContext = createContext<StateContextValue>({
  state: initialState,
  dispatch: (action: StateChangeRequest) => {
    console.log("No reducer for action " + action.type + '. Update State Provider')
  }
});

export function StateProvider({ children }: { children: React.ReactNode }) {
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
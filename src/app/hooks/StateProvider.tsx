import React, { createContext, useContext, useReducer } from "react";
import { ApplicationState } from "./ApplicationState";
import { BlockAddRequest } from "./BlockAddRequest";
import { BlockSelectRequest } from "./BlockSelectRequest";
import { StateChangeRequest, StateChangeRequestType } from "./StateChangeRequest";

const initialState = new ApplicationState();

function stateReducer(state: ApplicationState, action: StateChangeRequest) {
  console.log('Dispatching action ' + action.type)

  switch (action.type) {
    case StateChangeRequestType.BLOCK_ADD: {
      const payload = action as BlockAddRequest;
      return state.withBlock(payload.page, payload.block)
    }
    case StateChangeRequestType.BLOCK_SELECT: {
      const payload = action as BlockSelectRequest;
      return state.withCurrentBlock(payload.block);
    }
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
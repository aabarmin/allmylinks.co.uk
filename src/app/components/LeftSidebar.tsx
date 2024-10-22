import { useAppState } from "../hooks/StateProvider";
import { PageBuilderPane } from "./PageBuilderPane";
import { PageListPane } from "./PageListPane";

export function LeftSidebar() {
  const { state } = useAppState();
  switch (state.getCurrentLeftPane()) {
    case 'page-builder': return <PageBuilderPane />
    case 'page-management': return <PageListPane />
  }
  return <div>Unknown component {state.getCurrentLeftPane()}</div>
}
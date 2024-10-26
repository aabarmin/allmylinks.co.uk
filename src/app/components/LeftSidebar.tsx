import { PageBuilderPane } from "./PageBuilderPane";

export function LeftSidebar() {
  // todo: migrate to server components and render based on the route
  return <PageBuilderPane />
  // const { state } = useAppState();
  // switch (state.getCurrentLeftPane()) {
  //   case 'page-builder': return <PageBuilderPane />
  //   case 'page-management': return <PageListPane />
  // }
  // return <div>Unknown component {state.getCurrentLeftPane()}</div>
}
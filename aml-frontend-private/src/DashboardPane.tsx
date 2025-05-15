import { Col, Container, Row } from "react-bootstrap";
import { useLoaderData } from "react-router";
import BlocksAccordion from "./BlocksAccordion";
import Divider from "./Divider";
import UserLinkPreview from "./UserLinkPreview";
import type { DashboardModel } from "./model/DashboardModel";

export default function DashboardPane() {
  const model: DashboardModel = useLoaderData();

  return (
    <Container fluid>
      <Row>
        <Col>&nbsp;</Col>
      </Row>
      <Row>
        <Col md={4}>
          <UserLinkPreview profile={model.profile} />
          <Divider />
          <BlocksAccordion availableBlocks={model.availableBlocks} />
        </Col>
        <Col md={5}>
          {/* <DashboardPreview
            currentPage={{
              pageProps: null,
              pageBlocks: [],
            }}
          /> */}
        </Col>
        <Col md={3}>
          {/* {currentBlock === null ? (
            <DashboardPaneProps
              currentPage={{
                pageId: "",
                pageProps: { backgroundColor: "#ffffff" }, // Replace with actual data
              }}
              onSave={() => {
                console.log("Save action triggered");
              }}
            />
          ) : (
            (() => {
              const ConfigComponent = React.lazy(() =>
                import(`./blocks/${currentBlock.blockType.configComponent}`)
              );
              return (
                <React.Suspense fallback={<div>Loading...</div>}>
                  <ConfigComponent block={currentBlock} />
                </React.Suspense>
              );
            })()
          )} */}
        </Col>
      </Row>
    </Container>
  );
}
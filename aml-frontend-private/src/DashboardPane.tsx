import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import UserLinkPreview from "./UserLinkPreview";
import BlocksAccordion from "./BlocksAccordion";
import DashboardPreview from "./DashboardPreview";
import DashboardPaneProps from "./DashboardPaneProps";
import type { DashboardModel } from "./model/DashboardModel";
import { useLoaderData } from "react-router";

export default function DashboardPane() {
  const model: DashboardModel = useLoaderData();

  const currentBlock = null; // Replace this with the actual logic to get the current block

  return (
    <Container fluid>
      <Row>
        <Col md={4}>
          <UserLinkPreview profile={model.profile} />
          {/* <BlocksAccordion availableBlocks={[]} pageBlocks={[]} currentPageId={""} /> */}
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
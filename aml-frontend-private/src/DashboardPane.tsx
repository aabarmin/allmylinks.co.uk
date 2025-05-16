import { useCallback, useEffect, useState } from "react";
import { Col, Container, ProgressBar, Row } from "react-bootstrap";
import { Outlet } from "react-router";
import BlocksAccordion from "./BlocksAccordion";
import Divider from "./Divider";
import UserLinkPreview from "./UserLinkPreview";
import { DashboardModel } from "./model/DashboardModel";
import { getDashboard } from "./service/DashboardService";

export default function DashboardPane() {
  const [loading, setLoading] = useState<boolean>(true);
  const [dashboard, setDashboard] = useState<DashboardModel | undefined>(undefined);

  const reloadDashboard = useCallback(() => {
    setLoading(true);
    getDashboard().then(data => {
      setDashboard(data);
      setLoading(false);
    });
  }, [setLoading, setDashboard]);
  const onModelChanged = useCallback(() => {
    reloadDashboard();
  }, []);

  useEffect(() => {
    reloadDashboard();
  }, [reloadDashboard]);

  if (loading) {
    return (
      <ProgressBar animated now={100} />
    );
  }

  const model: DashboardModel = dashboard!;

  return (
    <Container fluid>
      <Row>
        <Col>&nbsp;</Col>
      </Row>
      <Row>
        <Col md={4}>
          <UserLinkPreview profile={model.profile} />
          <Divider />
          <BlocksAccordion
            availableBlocks={model.availableBlocks}
            currentPage={model.currentPage}
            onModelChanged={onModelChanged}
          />
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
          <Outlet />
        </Col>
      </Row>
    </Container>
  );
}
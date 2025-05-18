import { Container, Nav, Navbar } from "react-bootstrap";
import { BoxArrowInRight, BoxArrowLeft, House, PersonBadge, Radioactive, Robot } from "react-bootstrap-icons";
import { Outlet } from "react-router";

function DashboardHeader() {
    return (
        <Navbar expand="lg" data-bs-theme="dark" bg="primary" >
            <Container>
                <Navbar.Brand href="/">
                    <Robot />
                    &nbsp; AML
                </Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link href="/">
                        <House />
                        &nbsp; HOME
                    </Nav.Link>
                    <Nav.Link href="/private/dashboard2/index.html">
                        <BoxArrowInRight />
                        &nbsp; DASHBOARD
                    </Nav.Link>
                    <Nav.Link href="/private/profile">
                        <PersonBadge />
                        &nbsp; PROFILE
                    </Nav.Link>
                </Nav>
                <Nav>
                    <Nav.Link href="/backoffice" className="btn-outline-light">
                        <Radioactive />
                        &nbsp; BACKOFFICE
                    </Nav.Link>
                    <Nav.Link href="/private/logout">
                        <BoxArrowLeft />
                        &nbsp; LOGOUT
                    </Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    )
}

export default function DashboardLayout() {
    return (
        <>
            <DashboardHeader />
            <Outlet />
        </>
    );
}
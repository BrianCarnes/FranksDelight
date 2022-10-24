import { Box, Container, Card } from '@mui/material';
import { Helmet } from 'react-helmet-async';

import { styled } from '@mui/material/styles';
import Logo from '../../components/LogoSign';
import Home from './Home';


const OverviewWrapper = styled(Box)(
  () => `
    overflow: auto;
    flex: 1;
    overflow-x: hidden;
    align-items: center;
`
);

function Overview() {
  // @ts-ignore
  return (
    <>
      <OverviewWrapper>
        <Helmet>
          <title>Franks Delight</title>
        </Helmet>
        <Container maxWidth="lg">
          <Card sx={{ p: 10, mb: 2, borderRadius: 12, mt: 2 }}>
            <Home />
          </Card>
        </Container>
      </OverviewWrapper>
    </>
  );
}

export default Overview;

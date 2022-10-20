import { Helmet } from 'react-helmet-async';
import PageHeader from './PageHeader';
import PageTitleWrapper from '../../../components/PageTitleWrapper';
import { Container, Grid } from '@mui/material';
import Footer from '../../../components/Footer';


function PointOfSale() {
  return (
    <>
      <Helmet>
        <title>Franks Delight</title>
      </Helmet>
      <PageTitleWrapper >
        <PageHeader />
      </PageTitleWrapper>
      <Container maxWidth="lg">
        <Grid
          container
          direction="row"
          justifyContent="center"
          alignItems="stretch"
          spacing={4}
        >

        </Grid>
      </Container>
      <Footer />
    </>
  );
}

export default PointOfSale;

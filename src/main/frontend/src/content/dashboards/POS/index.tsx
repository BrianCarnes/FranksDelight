import React, { useState } from 'react';
import { Helmet } from 'react-helmet-async';
import PageHeader from './PageHeader';
import PageTitleWrapper from '../../../components/PageTitleWrapper';
import { Container, Grid } from '@mui/material';
import Footer from '../../../components/Footer';
import Menu from './MenuItems';
import { CartProvider } from 'react-use-cart'


function PointOfSale() {
  const [value, setValue] = React.useState(0);

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    console.log(event, newValue);
    setValue(newValue);
  };

  return (
    <>
      <Helmet>
        <title>Franks Delight</title>
      </Helmet>
      <PageTitleWrapper >
        <PageHeader
          handleChange={handleChange}
          value={value}
        />
      </PageTitleWrapper>
      <Container maxWidth="lg">
          <Menu
            value={value}
          />
      </Container>
      <Footer />
    </>
  );
}

export default PointOfSale;

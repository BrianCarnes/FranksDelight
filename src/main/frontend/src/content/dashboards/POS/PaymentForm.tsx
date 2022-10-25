import { useState } from 'react';
import { useStripe, useElements, CardElement, Elements } from '@stripe/react-stripe-js';
import axios from 'axios'
import { Button, Typography } from '@mui/material'
import { useCart } from 'react-use-cart'

const CARD_OPTIONS = {
  iconStyle: "solid",
  style: {
    base: {
      iconColor: "#333",
      color: "#333",
      fontWeight: 400,
      fontFamily: "Merriweather, Open Sans, Segoe UI, sans-serif",
      fontSize: "16px",
      fontSmoothing: "antialiased",
      ":-webkit-autofill": { color: "#333" },
      "::placeholder": { color: "#777" },
    },
    invalid: {
      iconColor: "#ffc7ee",
      color: "#ffc7ee"
    },
  }
}

export default function PaymentForm(props: any) {
  const { emptyCart } = useCart();
  const [success, setSuccess ] = useState(false)
  const stripe = useStripe()
  const elements = useElements()

  // if success is set to true, empty cart after 1 second
  if (success) {
    setTimeout(() => {
      emptyCart()
    }, 1000)
  }

  const handleSubmit = async (e: any) => {
    e.preventDefault()
    // @ts-ignore
    const {error, paymentMethod} = await stripe.createPaymentMethod({
      type: "card",
      card: elements.getElement(CardElement)
    })
    if(!error) {
      try {
        const {id} = paymentMethod
        const response = await axios.post("http://localhost:8080/api/payment/charge", {
          amount: props.amount
        })

        if(response.data.success) {
          console.log("Successful payment")
          setSuccess(true);
        }

      } catch (error) {
        console.log("Error", error)
        setSuccess(true);
      }
    } else {
      console.log(error.message)
    }
  }

  // @ts-ignore
  return (
    <>
      {!success ?
        <form onSubmit={handleSubmit}>
          <fieldset className="FormGroup">
            <div className="FormRow">
              <CardElement options={CARD_OPTIONS}/>
            </div>
          </fieldset>
          <Button type="submit" variant="contained" size="small" color="warning" sx={{mt:2}}>Pay</Button>
        </form>
        :
        <Typography variant="h3" >Payment in the amount of ${props.amount} succeeded!</Typography>
      }

    </>
  )
}
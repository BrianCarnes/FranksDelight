import { Elements } from "@stripe/react-stripe-js"
import { loadStripe } from "@stripe/stripe-js"
import React from "react"
import PaymentForm from "./PaymentForm"
import { PUBLIC_KEY } from "../../../../stripe_credentials.json"


const stripeTestPromise = loadStripe(PUBLIC_KEY)

export default function StripeContainer(props: any) {
  return (
    <Elements stripe={stripeTestPromise}>
      <PaymentForm amount={props.amount}/>
    </Elements>
  )
}
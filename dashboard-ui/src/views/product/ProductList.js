import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getProductListAsync } from "../../actions/product";

const productList = () => {

    const dispatch = useDispatch();
    const {productList} = useSelector(state => state.product);

    useEffect(() => {
        dispatch(getProductListAsync());
        console.log(productList);

    }, [])

    return(
        <>
        {productList && productList.map((p) => <p key={p.id}>{p.category} {p.amount} {p.price} </p>)}
        </>
    );
};

export default productList;
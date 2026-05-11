import React, { ComponentType, useEffect } from "react";

export const withDocumentTitle = <T extends object>(Component: ComponentType<T>) => (title?: string) => (props: T) => {

    useEffect(() => {
        document.title = title ? `${title} / StreamNet` : "StreamNet";
    }, []);

    return <Component {...props as T} />;
};

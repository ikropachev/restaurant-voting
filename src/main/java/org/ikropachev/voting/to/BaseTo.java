package org.ikropachev.voting.to;

import io.swagger.annotations.ApiModelProperty;
import org.ikropachev.voting.HasId;

public abstract class BaseTo implements HasId {
    @ApiModelProperty(readOnly = true)
    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
